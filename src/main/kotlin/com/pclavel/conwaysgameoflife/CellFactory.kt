package com.pclavel.conwaysgameoflife

import com.pclavel.conwaysgameoflife.CellStatus.*

object CellFactory {

    fun createCell(): Cell = Cell()

    class Cell internal constructor() {

        private var cellStatus: CellStatus = NOT_BORN
            private set

        private val TOO_LONELY_MAX = 1
        private val EXACT_NUMBER_OF_PARENTS = 3
        private val TOO_CROWDED_MIN = 4

        fun getCellStatus(): CellStatus {
            return cellStatus
        }

        fun rise(): Cell {
            cellStatus = ALIVE
            return this
        }

        private fun die(): Cell {
            cellStatus = DEAD
            return this
        }

        fun evolve(neighbours: Array<Cell>): Cell {
            val aliveNeighbours = neighbours.filter { cell -> cell.getCellStatus() == ALIVE }.count()
            var evolution = Cell()
            if (this.getCellStatus() == ALIVE) {
                if (aliveNeighbours <= TOO_LONELY_MAX || aliveNeighbours >= TOO_CROWDED_MIN) {
                    evolution.die() // new evolve is dead
                } else {
                    evolution = evolution.rise() // new evolve is cellStatus
                }
            } else { // cell is not born or dead
                if (aliveNeighbours == EXACT_NUMBER_OF_PARENTS) {
                    evolution = evolution.rise() // new evolve is to be born
                }
            }

            return evolution
        }

    }

}


