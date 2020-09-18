package com.tcasper.chess.board.models

abstract class Square {
  def isOccupied: Boolean
  def getPiece: Option[Piece]
}

object Square {

  private val EmptyBoard: Map[Int, EmptySquare] = initializeEmptyBoard

  def initializeEmptyBoard: Map[Int, EmptySquare] = {
    val emptyBoard = (for (
      i <- (0 to 63)
    ) yield (i, EmptySquare(i))).toMap
    emptyBoard
  }

  def apply(coordinate: Int, piece: Option[Piece]): Square = {
    piece match {
      case None => EmptySquare(coordinate)
      case Some(p) => FullSquare(coordinate, Some(p))
    }
  }
}

final case class EmptySquare(coordinate: Int) extends Square {

  override def isOccupied = false
  override def getPiece: Option[Piece] = None

}

final case class FullSquare(coordinate: Int, piece: Piece) extends Square {

  override def isOccupied: Boolean = true
  override def getPiece: Option[Piece] = Some(piece)

}


