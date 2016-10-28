//
//   Copyright 2016  Cityzen Data
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

package io.warp10.script.functions;

import io.warp10.script.NamedWarpScriptFunction;
import io.warp10.script.WarpScriptStackFunction;
import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;

import java.util.ArrayList;
import java.util.List;

import org.boon.json.JsonException;
import org.boon.json.JsonParser;
import org.boon.json.JsonParserFactory;

/**
 * Parses a String as JSON and pushes it onto the stack
 */
public class JSONTO extends NamedWarpScriptFunction implements WarpScriptStackFunction {
  
  private static final JsonParserFactory BOON_PARSER_FACTORY = new JsonParserFactory();

  public JSONTO(String name) {
    super(name);
  }
  
  @Override
  public Object apply(WarpScriptStack stack) throws WarpScriptException {
    Object o = stack.pop();
    
    if (!(o instanceof String)) {
      throw new WarpScriptException(getName() + " expects a string on top of the stack.");
    }
    
    JsonParser parser = BOON_PARSER_FACTORY.create();
    
    Object json = null;
    
    try {
      json = parser.parse(o.toString());
    } catch(JsonException je) {      
      // We don't include the original message as it can be very long
      throw new WarpScriptException("Error parsing JSON");
    }

    //
    // Do a simple replacement of Integers by Longs if we have a list
    //
    // The list is an abstract one which cannot be modified, so we need to create a new list
    if (json instanceof List) {
      List<Object> l = (List) json;
      List<Object> target = new ArrayList<Object>();
      
      for (int i=0; i < l.size(); i++) {
        if (l.get(i) instanceof Integer) {
          target.add(((Integer) l.get(i)).longValue()); 
        } else {
          target.add(l.get(i));
        }
      }
      
      stack.push(target);
    } else {
      stack.push(json);
    }    

    return stack;
  }
}
