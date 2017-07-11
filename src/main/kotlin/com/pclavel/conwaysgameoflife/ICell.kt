package com.pclavel.conwaysgameoflife

import java.util.UUID

interface ICell {
    fun getUUID(): UUID
    fun getStatus(): CellStatus
    fun rise(): ICell
    fun evolve(neighbours: List<ICell>): ICell
}