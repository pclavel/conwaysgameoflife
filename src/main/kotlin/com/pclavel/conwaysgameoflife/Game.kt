package com.pclavel.conwaysgameoflife


fun main(args: Array<String>) {
    val organism = Organism(3)
    organism.spawnCell(1,0)
    organism.spawnCell(1,1)
    organism.spawnCell(1,2)

    printOrganism(organism)
    for (i in 0..10) {
        organism.evolve()
        printOrganism(organism)
        Thread.sleep(5000)
    }
}

fun printOrganism(organism: Organism) {
    for(i in 0..organism.size-1){
        for(j in 0..organism.size-1){
            print(organism.tissue[i][j].getCellStatus().toString() + " ")
        }
        println()
    }
    println()
}
