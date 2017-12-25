package tests;
import java.AtomicMassCalculator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



public class AtomicMassCalculatorTest {

	 static AtomicMassCalculator atomic;

	    @BeforeClass
	    public static void init() {

	        atomic = new AtomicMassCalculator();
	        atomic.addElements();
	        atomic.AtomicWeightPrint();

	    }

	    @Test
	    public void shouldReturnWeightOfSingleAtomOfElement(){

	        Assert.assertEquals(1.00794,atomic.calculateAtomicMass("H"),0);
	    }

	    @Test
	    public void shouldReturnWeightOfMoleculesMadeOfMoreThanOneAtomOfElement(){


	        Assert.assertEquals(2*1.00794,atomic.calculateAtomicMass("H2"),0);
	        Assert.assertEquals(8*15.9994,atomic.calculateAtomicMass("O8"),0);
	        Assert.assertEquals(9*32.065,atomic.calculateAtomicMass("S9"),0);

	    }

	    @Test
	    public void shouldReturnWeightOfSimpleCompoundWithTwoElements(){


	        //example - (not valid)
	        Assert.assertEquals(1.00794+32.065, atomic.calculateAtomicMass("HS"),0);

	    }

	    @Test
	    public void shouldReturnWeightOfElementsWithTwoAlphabets(){


	        //example - (not valid)
	        Assert.assertEquals(35, atomic.calculateAtomicMass("Cl"),0);
	    }

	    @Test
	    public void shouldReturnWeightOfMultipleElementsMadeUpOfSingleAtom(){

	         Assert.assertEquals(1.00794+32.065+1.00794+32.065,atomic.calculateAtomicMass("HSHS"),0);
	         Assert.assertEquals(1.00794+32.065+35,atomic.calculateAtomicMass("HSCl"),0);
	         Assert.assertEquals(1.00794+32.065+35+1.00794+32.065,atomic.calculateAtomicMass("HSClHS"),0);

	    }

	    @Test
	    public void shouldReturnWeightOfMultipleElementsMadeUpOfMultipleSingleDigitAtoms(){

	        Assert.assertEquals(1.00794*2 + 15.9994,atomic.calculateAtomicMass("H2O"),0);
	        Assert.assertEquals(1.00794*2 + 32.065+ 15.9994 , atomic.calculateAtomicMass("H2SO"),0);
	        Assert.assertEquals(1.00794*2 + 32.065 + 15.9994 * 4 , atomic.calculateAtomicMass("H2SO4"),0);
	        Assert.assertEquals(1.00794*2 +32.065 * 4 , atomic.calculateAtomicMass("H2S4"),0);

	    }

	    @Test
	    public void shouldReturnWeightOfMultipleElementsMadeUpOfMultipleDoubleDigitAtoms(){

	        Assert.assertEquals(1.00794*22 + 15.9994,atomic.calculateAtomicMass("H22O"),0);
	        Assert.assertEquals(1.00794*2 + 32.065*999 + 15.9994*44 + 1.00794*36 ,atomic.calculateAtomicMass("H2S999O44H36"),0);

	        Assert.assertEquals(1.00794*2 + 35*9 +  32.065*999 + 35*87 + 15.9994*44 + 1.00794*36+35*45,atomic.calculateAtomicMass("H2Cl9S999Cl87O44H36Cl45"),0);
	        Assert.assertEquals(1.00794*22 + 35*824 + 15.9994,atomic.calculateAtomicMass("H22Cl824O"),0);

	    }


	     @Test
	    public void shouldReturnWeightOfComplexMoleculesWithParenthesis(){

	         Assert.assertEquals(1.00794 + 35 + (32.065+1.00794+ 35) + 15.9994*4 , atomic.calculateAtomicMass("HCl(SHCl)O4"),0);
	         Assert.assertEquals(1.00794 + (32.065+1.00794+ 35) + 15.9994*4 , atomic.calculateAtomicMass("H(SHCl)O4"),0);
	         Assert.assertEquals(1.00794 + 35 +32.065 + 15.9994 + (32.065+1.00794+ 35) + 15.9994*4 + 35 , atomic.calculateAtomicMass("HClSO(SHCl)O4Cl"),0);
	     }


	    @Test
	    public void shouldReturnWeightOfComplexMoleculesWithMultuplierForParenthesis(){

	        Assert.assertEquals(1.00794 + 35 + (32.065+1.00794+ 35)*2+ 15.9994*4 , atomic.calculateAtomicMass("HCl(SHCl)2O4"),0);
	        Assert.assertEquals(1.00794 + 35 + (32.065+1.00794+ 35)*29+ 15.9994*4 , atomic.calculateAtomicMass("HCl(SHCl)29O4"),0);
	        Assert.assertEquals(1.00794 + 35 + (32.065+1.00794+ 35)*288 + 1.00794 + (15.9994+1.00794)*4 , atomic.calculateAtomicMass("HCl(SHCl)288H(OH)4"),0);


	    }
	}

