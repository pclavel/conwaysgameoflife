package com.pclavel.conwaysgameoflife

import com.pclavel.conwaysgameoflife.CellFactory.Cell

class Organism constructor(organismSize: Int) {
    val size: Int = organismSize //TODO check valid values of organism size
    var tissue = Array(organismSize, { Array(organismSize, { CellFactory.createCell() }) })

    fun spawnCell(i: Int, j: Int) {
        assert(i > 0 && i < this.size)
        assert(j > 0 && j < this.size)

        tissue[i][j].rise()
    }

    //TODO how to test this!
    fun evolve() {
        val newTissue = Array(size, { Array(size, { CellFactory.createCell() }) })
        for (i in 0..size-1) {
            for (j in 0..size-1) {
                newTissue[i][j] = tissue[i][j].evolve(getNeighboursOf(i, j))
            }
        }
        tissue = newTissue
    }

    private fun getNeighboursOf(i: Int, j: Int): Array<Cell> {
        // 0 1 2
        // 3 C 4
        // 5 6 7
        return arrayOf(
                getNeighbour(i - 1, j - 1),
                getNeighbour(i - 1, j),
                getNeighbour(i - 1, j + 1),
                getNeighbour(i, j - 1),
                getNeighbour(i, j + 1),
                getNeighbour(i + 1, j - 1),
                getNeighbour(i + 1, j),
                getNeighbour(i + 1, j + 1)
        )
    }

    private fun getNeighbour(i: Int, j: Int): Cell {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            return CellFactory.createCell()
        } else {
            return tissue[i][j]
        }
    }
}