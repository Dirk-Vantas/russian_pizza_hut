import com.raylib.Jaylib;


/**
 * The type Pizza math.
 */
public class pizza_math {

    /**
     * Calc angle double.
     *
     * @param start_point the start point
     * @param end_point   the end point
     * @return the double
     */
//calculates polar vector angle from start and end point
    public static double calcAngle(Jaylib.Vector2 start_point, Jaylib.Vector2 end_point)
    {
        //calculate in what angle player is looking
        double Angle = Math.atan2(start_point.y()-end_point.y(),start_point.x()-end_point.x()) * 180 / Math.PI;
        if(Angle < 0)
        {
            //if angle lower than 0 add 360 to fix for full circle solution
            Angle = Angle + 360;
        }
        //double degrees = (Angle + 360) % 360;
        return Angle;
    }

    /**
     * Gets x.
     *
     * @param length_R the length r
     * @param Angle    the angle
     * @return the x
     */
    public static double getX(double length_R, double Angle)
    {
        return length_R * Math.cos(Math.toRadians(Angle));
    }

    /**
     * Gets y.
     *
     * @param length_R the length r
     * @param Angle    the angle
     * @return the y
     */
    public static double getY(double length_R, double Angle)
    {
        return length_R * Math.sin(Math.toRadians(Angle));
    }

    /**
     * Gets vec magnitude.
     *
     * @param x the x
     * @param y the y
     * @return the vec magnitude
     */
    public static double getVecMagnitude(double x,double y)
    {
        return Math.sqrt((Math.pow(x,2))+(Math.pow(y,2)));
    }

    /**
     * Gets vec distance.
     *
     * @param startpoint the startpoint
     * @param endpoint   the endpoint
     * @return the vec distance
     */
    public static double getVecDistance(Jaylib.Vector2 startpoint, Jaylib.Vector2 endpoint)
    {
        //get vector difference
        Jaylib.Vector2 distance_vector = new Jaylib.Vector2(startpoint.x()-endpoint.x(),startpoint.y()-endpoint.y());
        return getVecMagnitude(distance_vector.x(),distance_vector.y());
    }
}
