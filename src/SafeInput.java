import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput
{

    /**
     * gets a String value from the user which must be at least 1 character
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return String that is at least 1 character
     */

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retVal = "";

        do
        {
            System.out.print(prompt + ": ");
            retVal= pipe.nextLine();
            if(retVal.isEmpty())
            {
                System.out.println("You must enter at least 1 character!");
            }
        }while(retVal.isEmpty());

        return retVal;
    }



    /**
     * gets an unconstrained integer value from the user which must be at least 1 character
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return a int value that is at least 1 character
     */


    public static int getInt(Scanner pipe, String prompt)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.println(prompt + ": ");
            if(pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer not " + trash);
            }
        }while(!done);

        return retVal;

    }


    /**
     * gets an unconstrained double value from the user which must be at least 1 character
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return a double value that is at least 1 character
     */


    public static double getDouble(Scanner pipe, String prompt)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.println(prompt + ": ");
            if(pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double not " + trash);
            }
        }while(!done);

        return retVal;

    }


    /**
     * gets an int value from the user within the specified inclusive range
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return an int that is in the specified range
     */


    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.println(prompt + "[" + low + " - " + high + "]: ");
            if(pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if(retVal>= low && retVal <= high) {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a valid int in range [" + low + " - " + high + "]");
                }

            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer not, " + trash);
            }
        }while(!done);

        return retVal;

    }


    /**
     * gets a double value from the user within the specified inclusive range
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return a double within the specified range
     */


    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.println(prompt + "[" + low + " - " + high + "]: ");
            if(pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if(retVal>= low && retVal <= high) {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a valid double in range [" + low + " - " + high + "]");
                }

            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double not " + trash);
            }
        }while(!done);

        return retVal;

    }

    /**
     * gets a String value from the user of [YyNn]
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return true for Yy (yes) and false for Nn (no)
     */

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String input = "";
        boolean retVal = false;
        boolean done = false;

        do
        {
            System.out.print(prompt + "[YyNn]: ");
            input = pipe.nextLine();
            if(input.isEmpty())
            {
                System.out.println("You must enter a y or n!");
            }
            else if(input.equalsIgnoreCase("Y"))
            {
                retVal = true;
                done = true;
            }
            else if(input.equalsIgnoreCase("N"))
            {
                retVal = false;
                done = true;
            }else
            {
                System.out.println("You must enter Y or N, not: " + input);
            }


        }while(!done);

        return retVal;
    }
    /**
     * gets a String regular expression value from the user
     *
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @param regEx pattern for the regular expression to follow
     * @param format plain text pattern for the user to follow
     * @return an expression following the specified format
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx, String format)
    {
        String retVal = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + "["+ format + "]: ");
            retVal = pipe.nextLine();
            Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(retVal);
            boolean matchFound = matcher.find();

            if(retVal.isEmpty())
            {
                System.out.println("You must enter an expression!");
            }
            else if(matchFound)
            {
                done = true;
            }

            else
            {
                System.out.println("You must enter an expression following the pattern " + "["+ format + "], not " + retVal);
            }


        }while(!done);

        return retVal;
    }

    /**
     * gets a String regular expression value from the user
     *
     *
     * @param msg the message to display in the header
     * @return an expression following the specified format
     */

    public static String prettyHeader(String msg)
    {
        boolean done = false;
        int headerLength = msg.length();
        do{

            if(msg.isEmpty())
            {
                System.out.println("You must enter a message!");
            }
            else if(headerLength>54)
            {
                System.out.println("Your header cannot exceed 54 characters");
            }

            else
            {
                for (int i = 1; i <= 60 ; i++) {
                    System.out.print("*");
                }
                System.out.println();

                for (int i = 1; i <= 3 ; i++) {
                    System.out.print("*");
                }

                int indent = (54-headerLength)/2;

                for(int i=1; i<=indent ; i++){
                    System.out.print(" ");
                }
                System.out.print(msg);
                for(int i=1; i<=indent ; i++){
                    System.out.print(" ");
                }
                for (int i = 1; i <= 3 ; i++) {
                    System.out.print("*");
                }

                System.out.println();

                for (int i = 1; i <= 60 ; i++) {
                    System.out.print("*");
                }
                done = true;

            }


        }while(!done);

        return msg;



    }

}
