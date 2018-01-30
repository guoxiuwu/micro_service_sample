package com.ebao.gs.sp.rc.common.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.node.IntNode

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class LimitQueueSerializer extends JsonSerializer<LimitQueue> {

    @Override
    void serialize(LimitQueue value, JsonGenerator gen, SerializerProvider serializers) {
        gen.writeStartObject()
        gen.writeStringField("itemData",JSONUtils.toJSON(value))
        gen.writeNumberField("maxSize", value.limitSize())
        gen.writeEndObject()
    }
}


class LimitQueueDeserializer extends JsonDeserializer<LimitQueue> {
    @Override
    public LimitQueue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser)
        int maxSize = (Integer) ((IntNode) node.get("maxSize")).numberValue()
        def itemDataList = JSONUtils.fromJSONAsList(node.get("itemData").asText(),Object.class)
        LimitQueue fixedList = new LimitQueue(maxSize)
        itemDataList?.each {
            fixedList.add(it)
        }
        return fixedList
    }
}
