package ch.supsi.mineweeper.backend.business;

public interface Expandable {
    int getNearBombs();
    boolean isExpandable(); // oppure lascia solo getNearBombs()
}

