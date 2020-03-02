// fp.java

/**
 * Class that converts integers into floating point values, adds or multiplies
 * them, then returns the new value as int.
 * @author Lawrence Chua and Professor David Johannson
 */
public class fp
{
	// Be sure to put your name on this next line...
        /**
         * Returns programmer's name
         * @return Programmer's name
         */
	public String myName()
	{
		return "Lawrence Chua";
	}

        /**
         * Converts two integers into floating point,
         * adds the two values, then returns the new
         * value as an integer.
         * @param a First integer input
         * @param b Second integer input
         * @return Added number back as an integer.
         */
	public int add(int a, int b)
	{
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);

		// Put your code in here!
                
                //1. Handle exception values (NaN, zero, infinity)
                if ( fa.isNaN() == true || fb.isNaN() == true )
                {
                    if ( fa.isNaN() == true )
                    {
                        result = fa;
                        return result.asInt();
                    }
                    else
                    {
                        result = fb;
                        return result.asInt();
                    }
                }
                else if ( fa.isZero() == true || fb.isZero() == true) {
                    if ( fa.isZero() == true ) {
                        result = fb;
                        return result.asInt();
                    }
                    else {
                        result = fa;
                        return result.asInt();
                    }
                }
                else if ( fa.isInfinity() == true && fb.isInfinity() == true ) {
                      if ( fa.s() == fb.s() ) {
                          result = fa;
                          return result.asInt();
                      }
                      else {
                          //Setting result to NaN
                          result.setE( 255 );
                          result.setF( 12 );
                          //Setting result's sign to either sign (fa's)
                          result.setS( fa.s() );
                          return result.asInt();
                      }
                }
                else if ( fa.isInfinity() == true || fb.isInfinity() == true) {
                    if ( fa.isInfinity() == true ) {
                        result = fa;
                        return result.asInt();
                    }
                    else {
                        result = fb;
                        return result.asInt();
                    }
                }
                else {
                    System.out.println("No exceptions detected");
                }
                
                //2. Sort numbers
                
		return result.asInt();
	}

        /**
         * Converts two integers into floating point,
         * multiplies the two values, then returns the new
         * value as an integer.
         * @param a First integer input
         * @param b Second integer input
         * @return Multiplied number back as an integer.
         */
	public int mul(int a, int b)
	{
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);

		// Put your code in here!
                
                //1. Handle exception values (NaN, zero, infinity)
                if ( fa.isNaN() == true || fb.isNaN() == true )
                {
                    if ( fa.isNaN() == true )
                    {
                        result = fa;
                        return result.asInt();
                    }
                    else
                    {
                        result = fb;
                        return result.asInt();
                    }
                }
                else if ( fa.isZero() == true && fb.isInfinity() == true || 
                        fb.isZero() == true && fa.isInfinity() == true ) {
                    //Set result to NaN
                    result.setE( 255 );
                    result.setF( 12 );
                    //Setting result's sign to either sign (fa's)
                    result.setS( fa.s() );
                    return result.asInt();
                }
                else if ( fa.isZero() == true || fb.isZero() == true) {
                    if ( fa.isZero() == true ) {
                        result = fb;
                        //Setting sign of result
                        if ( fa.s() == fb.s() ) {
                            result.setS(1);
                        }
                        else {
                            result.setS(-1);
                        }
                        return result.asInt();
                    }
                    else {
                        result = fa;
                        //Setting sign of result
                        if ( fa.s() == fb.s() ) {
                            result.setS(1);
                        }
                        else {
                            result.setS(-1);
                        }
                        return result.asInt();
                    }
                }
                else if ( fa.isInfinity() == true || fb.isInfinity() == true ) {
                        result = fa;
                        //Setting sign of result
                        if ( fa.s() == fb.s() ) {
                            result.setS(1);
                        }
                        else {
                            result.setS(-1);
                        }
                        return result.asInt();
                }
                else {
                    System.out.println("No exceptions detected");
                }
                
                //Setting sign of result
                if ( fa.s() == fb.s() ) {
                    result.setS(1);
                }
                else {
                    result.setS(-1);
                }
                
                //Leading 1 and guard 0's already added to F field.
                
                //Adding exponents and subtracting -127 bc of bias
                result.setE(addingExpo(fa, fb));
                
                //Expo overflow -> Set result to Infinity
                if ( result.e() > 254 ) {
                    result.setE(5);
                    result.setF(0);
                    return result.asInt();
                }
                //Expo underflow -> Set result to 0
                else if ( result.e() < 0 ) {
                    result.setE(0);
                    result.setF(0);                    
                    return result.asInt();
                }
                else {
                    System.out.println("No overflow or underflow detected");
                }
                
                //Multiply fractions
                long newLongF = fa.f() * fb.f(); //52 bits long now
                //Need to remove the bottom 25 bits
                
                //>> signed shift
                //>>> unsigned shift
                //I chose signed shift bc long declared is signed by default
                
                newLongF >>= 25;
                
                //To normalize the number, shift right 1 positon
                newLongF >>= 1;
                
                //Then increment the expo
                result.setE(result.e() + 1);
                result.setF(newLongF);
                
                //2. Sort numbers
                //Remember that denormalized number '0' is the only test case
                
                //3. Multiply the significands
                
                //4. Normalize and round
                
                
		return result.asInt();
	}
        
        /**
         * 
         * @param faIn fa Input
         * @param fbIn fb Input
         * @return Added integer value without the 127 bias.
         */
        public int addingExpo( FPNumber faIn, FPNumber fbIn ) {
            return faIn.e() + fbIn.e() - 127; //-127 once?
        }
        
        public boolean unitTest(  ) {
            return false;
            //long long t = fa.f() + (fb.f() >> 3); //Add hint: aligning exponents
            
            /*
            //When normalizing the value after an add, we need to see if the 27th bit is set,
            //indicating an overflow. This test checks to see if the 27th bit is set:

            //In other cases, you need to check bit 26, so you would shift by 25.
            if ( ((t >> 26) & 1) == 1)
                {
                 // yes it is!
                }
            */
            
        }
        
	// Here is some test code that one student had written...
	public static void main(String[] args)
	{
		int v24_25	= 0x41C20000; // 24.25
		int v_1875	= 0xBE400000; // -0.1875
		int v5		= 0xC0A00000; // -5.0

		fp m = new fp();

                //Prints out really long numbers
                System.out.println(v24_25);
                System.out.println(v_1875);
                System.out.println(v5);
                
                //Prints out values that are float
                System.out.println(Float.intBitsToFloat(v24_25));
                System.out.println(Float.intBitsToFloat(v_1875));
                System.out.println(Float.intBitsToFloat(v5));
                
                //All test cases print out "0.0" because methods are not finished yet.
		System.out.println(Float.intBitsToFloat(m.add(v24_25, v_1875)) + " should be 24.0625");
		System.out.println(Float.intBitsToFloat(m.add(v24_25, v5)) + " should be 19.25");
		System.out.println(Float.intBitsToFloat(m.add(v_1875, v5)) + " should be -5.1875");

		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v_1875)) + " should be -4.546875");
		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v5)) + " should be -121.25");
		System.out.println(Float.intBitsToFloat(m.mul(v_1875, v5)) + " should be 0.9375");
	}
}
