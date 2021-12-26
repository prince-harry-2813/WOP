package com.plainid.assignment.service;

import com.plainid.assignment.dao.PokemonType;
import com.plainid.assignment.dao.primitives.Trainer;

public class Battle {

    String status="";
    String message="";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Calculate battle results between to Trainers
     * @param t1 Trainer 1
     * @param t2 Trainer 2
     * @return response of the battle results
     */
    public Battle calculateFight(Trainer t1, Trainer t2) {
        if (t1.getBag().size() != 3 || t2.getBag().size() != 3||t1.getId()==t2.getId()) {
            setMessage("canceled");
            setStatus("Error");
            return this;
        }
        int result = 0;
        for (int i = 0; i < 3; i++) {
            PokemonType pokemonType = PokemonType.Fire;
            int p1 = t1.getBag().get(i).getType().getValue();
            int p2 = t2.getBag().get(i).getType().getValue();

            //draw
            if (p1 == p2)
                continue;
            //Trainer 2 wins
            if ((p1 + 1) % 3 == p2) {
                result--;
            }
            // Trainer 1 wins
            else result++;
        }
        if (result == 0) {
            setStatus("Draw");
            setMessage("draw");
            t1.setLevel(t1.getLevel()+1);
            t2.setLevel(t2.getLevel()+1);
            return this;
        }
        setStatus("Success");
        if (result > 0) {
            setMessage(t1.getName() + " wins");
        t1.setLevel(t1.getLevel()+2);
        }
        else {setMessage(t2.getName() + " wins");
        t2.setLevel(t2.getLevel()+2);
        }
        return this;
    }

}
