package com.hybris.api.poc.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import java.io.IOException;

/**
 * TODO provide a custom setup for expected types in order of occurrence
 */
class OneOfDeserializerUtility {

    static Object deserialize(final JsonParser jp, final Class[] supportedTypes) throws IOException {

        final ObjectMapper oc = getCurrentObjectMapper(jp);
        JsonNode node = jp.readValueAsTree();
        final String nodeAsString = getNodeAsText(oc, node);

        for (final Class tryClass : supportedTypes) {
            try {


                if (tryClass.isPrimitive()) {
                    return asPrimitive(tryClass, node);
                }

                return oc.readValue(nodeAsString, tryClass);
            } catch (JsonProcessingException e) {
                System.out.println("Can not read '" + jp.getText() + "' as " + tryClass);
            }
        }
        throw new JsonMappingException("Could not serialize properly " + jp.getText() + " as one of " + Joiner.on(",").join(supportedTypes));

    }

    private static String getNodeAsText(ObjectMapper oc, JsonNode node) throws JsonProcessingException {
        final Object nodeAsObject = oc.treeToValue(node, Object.class);
        return oc.writeValueAsString(nodeAsObject);
    }

    private static ObjectMapper getCurrentObjectMapper(JsonParser jp) {
        return (ObjectMapper) jp.getCodec();
    }


    private static Object asPrimitive(final Class tryClass, final JsonNode node) {
        if (tryClass == String.class) {
            return node.asText();
        } else if (tryClass == Number.class) //???
        {
            return node.asInt();

        }
        if (tryClass == Boolean.class) {
            return node.asBoolean();
        } else {
            throw new IllegalArgumentException("type not supported " + tryClass);
        }
    }


}
