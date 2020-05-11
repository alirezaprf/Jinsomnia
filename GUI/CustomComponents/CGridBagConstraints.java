package CustomComponents;

import java.awt.GridBagConstraints;
import java.awt.Insets;
public class CGridBagConstraints extends GridBagConstraints{

    /**
     *
     */
    private static final long serialVersionUID = -5125649590552984681L;

    public CGridBagConstraints()
    {
        weightx=1;
        weighty=1;
        gridx=0;
        gridy=0;
        anchor=NORTHWEST;
        insets=new Insets(1,1,1,1);
    }
}