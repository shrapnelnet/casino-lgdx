package com.shr4pnel.casino.slots;
import com.shr4pnel.casino.util.AsciiArt.Fruits;

import java.util.ArrayList;
import java.util.List;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;

public class SlotsGame extends Game{

private List<Fruits[]> reals = new ArrayList<>();
private SlotsReal slotsReal = new SlotsReal();

public SlotsGame(){
   spin();



}
public List<Fruits[]> getReals(){
   return reals;
}

public void spin(){
    reals.clear();
    reals.add(slotsReal.getReal(10));
    reals.add(slotsReal.getReal(10));
    reals.add(slotsReal.getReal(10));

}
@Override
    public Player getPlayer() {
       return null;
    }

    @Override
    public Player getAi() {
       return null;
    }

   }
