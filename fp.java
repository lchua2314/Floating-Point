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
                          result.setF( 1 );
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
                          result.setF( 1 );
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
                
                //2. Sort numbers
                //Remember that denormalized number '0' is the only test case
                
		return result.asInt();
	}

        //1. Handle exception values (NaN, zero, infinity)
        public int checkExceptions(FPNumber fa, FPNumber fb)
        {
            if ( fa.isNaN() == true || fb.isNaN() == true )
            {
                return 1;
            }
            
            
            return 1;
        }
        
        public boolean unitTest(  ) {
            return false;
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
