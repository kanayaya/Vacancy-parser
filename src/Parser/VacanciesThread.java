package Parser;


import java.util.ArrayList;
import java.util.NoSuchElementException;

public class VacanciesThread extends Thread {
    private volatile ArrayList<VacancyBlock> vacancies = null;
    private final VacanciesFabric fabric;

    public VacanciesThread(VacanciesFabric fabric) {
        this.fabric = fabric;
        this.setPriority(2);
    }
    public ArrayList<VacancyBlock> getVacancies() {
        while (vacancies == null) ;//wait
        return vacancies;
    }
    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("Taking vacancies...");
                this.vacancies = fabric.getVacancies();
            } catch (NoSuchElementException e) {
                System.out.println("Error in " + fabric.getClass().toString() + ". Maybe wrong key/value or target changed attribute:  " + e);
            }
        }
    }
}

