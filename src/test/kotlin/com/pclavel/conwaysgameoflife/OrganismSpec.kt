package com.pclavel.conwaysgameoflife

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class OrganismSpec : Spek({

    given("an organism with size 3") {
        val organism = Organism(3)
        on("creation") {
            it("has 3x3 cells no cell is alive yet") {
                assertEquals(3, organism.size)
                assertEquals(3, organism.tissue.size)
                assertEquals(3, organism.tissue[0].size)
                assertEquals(3, organism.tissue[1].size)
                assertEquals(3, organism.tissue[2].size)
                for (tissueRow in organism.tissue) {
                    for (cell in tissueRow) {
                        assertNotEquals(CellStatus.ALIVE, cell.getCellStatus())
                    }
                }
            }
        }

        on("spawn of the cell (2,2)") {
            organism.spawnCell(2, 2)
            it("should be alive") {
                assertEquals(CellStatus.ALIVE, organism.tissue[2][2].getCellStatus())
            }
        }

        on("evolve") {
            organism.evolve()
            it("should ask all cells to evolve") {

            }
        }
    }
})