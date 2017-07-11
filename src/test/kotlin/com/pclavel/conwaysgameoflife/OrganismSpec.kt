package com.pclavel.conwaysgameoflife

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.assertEquals

class OrganismSpec : Spek({

    given("an organism with size 3") {
        val mockedCellFactory = mock(CellFactory::class.java)
        val mockedCell = mock(Cell::class.java)
        `when`(mockedCellFactory.createCell()).thenReturn(mockedCell)

        val organism = Organism(3, mockedCellFactory)
        on("creation") {
            it("has 3x3 cells") {
                assertEquals(3, organism.size)
                assertEquals(3, organism.tissue.size)
                assertEquals(3, organism.tissue[0].size)
                assertEquals(3, organism.tissue[1].size)
                assertEquals(3, organism.tissue[2].size)
            }
        }

        on("spawn of the cell (2,2)") {
            organism.spawnCell(2, 2)
            it("organism should call rise() on the cell") {
                verify(mockedCell, Mockito.times(1)).rise()
            }
        }

        on("evolve") {
            organism.evolve()
            it("should ask all cells to evolve") {
                //TODO verify the list of neightbours is correct
                verify(mockedCell, Mockito.times(9)).evolve(ArgumentMatchers.anyList<ICell>())
            }
        }
    }
})