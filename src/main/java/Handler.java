import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Handler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        try {

            // Extract inputString
            context.getLogger().log("Received input: " + input.toString());


            // Get the body as a string
            String body = (String) input.get("body");

            // Log the raw body for debugging purposes
            context.getLogger().log("Received body: " + body);

            // Parse the body into a Map using ObjectMapper (from Jackson)
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> bodyMap = objectMapper.readValue(body, Map.class);

            // Extract stringArray (as ArrayList<ArrayList<String>>)
            ArrayList<ArrayList<String>> stringArray = (ArrayList<ArrayList<String>>) bodyMap.get("stringArray");
            String inputString = (String) bodyMap.get("inputString");
            List<String> rack = new ArrayList<>(Arrays.asList(inputString.split("")));

            // Convert the ArrayList<ArrayList<String>> to a String[][] array (optional)
            int rows = stringArray.size();
            int cols = stringArray.get(0).size();
            String[][] stringArray2D = new String[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    stringArray2D[i][j] = stringArray.get(i).get(j);
                }
            }
            List<Move> moves = Util.findPossibleMoves(stringArray2D, rack);
            if (moves.isEmpty()) return null;
            Move top = moves.get(0);
            List<Placement> placements = top.getPlacements();
            ObjectMapper objectMapper2 = new ObjectMapper();

            try {
                // Serialize the list of Placement objects into a JSON string
                String jsonResponse = objectMapper2.writeValueAsString(top);

                // Return the JSON response
                return jsonResponse;

            } catch (JsonProcessingException e) {
                context.getLogger().log("Error serializing placement objects: " + e.getMessage());
                return "{ \"error\": \"Failed to process placements\" }";
            }


//            // Now you have inputString and stringArray2D (the 2D string array)
//            context.getLogger().log("Received inputString: " + inputString);
//            context.getLogger().log("Received 15x15 array with first element: " + stringArray2D[0][0]);
//            context.getLogger().log("Moves " + moves);
//            for (Move move : moves) {
//                context.getLogger().log(move.toString());
//            }
//            // Process the data as needed and return a result
//            return moves.toString();
        } catch (Exception e) {
            context.getLogger().log("Error occurred: " + e.getMessage());
            return "Error occurred: " + e.getMessage();
        }
    }
}
