package com.pclavel.conwaysgameoflife

import java.util.UUID

class Cell : ICell {

    private var cellStatus: CellStatus = CellStatus.NOT_BORN
        private set

    private val uuid: UUID = UUID.randomUUID()

    private val TOO_LONELY_MAX = 1
    private val EXACT_NUMBER_OF_PARENTS = 3
    private val TOO_CROWDED_MIN = 4

    override fun getUUID(): UUID {
        return uuid
    }

    override fun getStatus(): CellStatus {
        return cellStatus
    }

    override fun rise(): Cell {
        cellStatus = CellStatus.ALIVE
        return this
    }

    fun die(): ICell {
        cellStatus = CellStatus.DEAD
        return this
    }

    override fun evolve(neighbours: List<ICell>): ICell {
        val aliveNeighbours = neighbours.filter { cell -> cell.getStatus() == CellStatus.ALIVE }.count()
        var evolution = Cell()
        if (this.getStatus() == CellStatus.ALIVE) {
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
