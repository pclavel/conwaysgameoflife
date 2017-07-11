package com.pclavel.conwaysgameoflife

import java.util.Random


fun main(args: Array<String>) {
    val organism = organismOf10x()

    printOrganism(organism)
    for (i in 0..50) {
        organism.evolve()
        printOrganism(organism)
        Thread.sleep(1000)
    }
}

private fun organismOf3x(): Organism {
    val organism = Organism(3, CellFactory())
    organism.spawnCell(1, 0)
    organism.spawnCell(1, 1)
    organism.spawnCell(1, 2)
    return organism
}

private fun organismOf10x(): Organism {
    val organism = Organism(10, CellFactory())
    val randomInts = Random()
    for (x in 1..20) {
        val i = randomInts.nextInt(10)
        val j = randomInts.nextInt(10)
        organism.spawnCell(i, j)
    }

    return organism
}


fun printOrganism(organism: Organism) {
    for(i in 0..organism.size-1){
        for(j in 0..organism.size-1){
            print(organism.tissue[i][j].getStatus().toString() + " ")
        }
        println()
    }
    println()
}
