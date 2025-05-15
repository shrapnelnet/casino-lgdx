package com.shr4pnel.casino.slots;

public abstract class SlotsFruit{
    
    protected int fruitValue;
   

    public SlotsFruit( int fruitValue, String fruitType) {
        this.fruitValue = fruitValue;
        
    }
 

    public int getfruitValue() {
        return fruitValue;
    }
 


}
