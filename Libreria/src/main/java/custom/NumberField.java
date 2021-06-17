package custom;

import javafx.scene.control.TextField;

public class NumberField extends TextField{

    @Override
    public void replaceText(int start, int end, String text) {
        if(text.matches("[0-9]") || text.trim().equals("")){
            super.replaceText(start, end, text);
        }
    }
    
    
}
