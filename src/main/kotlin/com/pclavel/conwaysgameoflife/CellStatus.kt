package com.pclavel.conwaysgameoflife

enum class CellStatus {
    NOT_BORN, ALIVE, DEAD;

    override fun toString(): String{
        return when (this) {
            NOT_BORN -> " "
            ALIVE -> "O"
            DEAD -> "*"
        }
    }


}