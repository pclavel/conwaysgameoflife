package com.pclavel.conwaysgameoflife

import com.pclavel.conwaysgameoflife.CellFactory.Cell
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class CellSpec : Spek({

    fun getNeighbourhood(howManyAlive: Int): List<Cell> {
        val list = mutableListOf<Cell>()
        for (i in 1..howManyAlive) {
            list.add(CellFactory.createCell().rise())
        }
        for (i in howManyAlive+1..8){
            list.add(CellFactory.createCell())
        }
        return list
    }

    given("a cell is created") {
        val cell = CellFactory.createCell()
        on("life evaluation") {
            val alive = cell.getCellStatus()
            it("should say is not born") {
                assertTrue { CellStatus.NOT_BORN == alive }
            }
        }
        on("birth") {
            val alive = cell.rise().getCellStatus()
            it("should say is alive") {
                assertTrue { CellStatus.ALIVE == alive }
            }
        }
    }

    given("a cell is alive ") {
        val cell = CellFactory.createCell().rise()

        on("evolve when all neighbours are dead") {
            val neighbours = getNeighbourhood(0)
            val evolution = cell.evolve(neighbours)
            it("should die") {
                assertTrue { evolution.getCellStatus() == CellStatus.DEAD }
            }
        }
        on("evolve when only 1 neighbour is alive") {
            val neighbours = getNeighbourhood(1)
            val evolution = cell.evolve(neighbours)
            it("should die") {
                assertTrue { evolution.getCellStatus() == CellStatus.DEAD }
            }
        }
        on("evolve when more than 3 neighbours are alive") {
            // TODO we should test for 4, 5, 6, 7 and 8
            val neighbours = getNeighbourhood(4)
            val evolution = cell.evolve(neighbours)
            it("should die") {
                assertTrue { evolution.getCellStatus() == CellStatus.DEAD }
            }
        }
        on("evolve when 2 neighbours are alive") {
            val neighbours = getNeighbourhood(2)
            val evolution = cell.evolve(neighbours)
            it("should stay alive") {
                assertTrue { evolution.getCellStatus() == CellStatus.ALIVE }
            }
        }
        on("evolve when 3 neighbours are alive") {
            val neighbours = getNeighbourhood(3)
            val evolution = cell.evolve(neighbours)
            it("should stay alive") {
                assertTrue { evolution.getCellStatus() == CellStatus.ALIVE }
            }
        }
    }

    given("a cell is not born ") {
        val cell = CellFactory.createCell()
        on("evolve when 3 neighbours are alive") {
            val neighbours = getNeighbourhood(3)
            val evolution = cell.evolve(neighbours)
            it("should born") {
                assertTrue { evolution.getCellStatus() == CellStatus.ALIVE }
            }
        }
    }

})