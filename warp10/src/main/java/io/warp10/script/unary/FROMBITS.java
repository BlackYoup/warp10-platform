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

package io.warp10.script.unary;

import io.warp10.script.NamedWarpScriptFunction;
import io.warp10.script.WarpScriptStackFunction;
import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;

/**
 * Converts the long on top of the stack into a double by considering it a raw bit representation
 */
public class FROMBITS extends NamedWarpScriptFunction implements WarpScriptStackFunction {

  private final boolean asFloat;
  
  public FROMBITS(String name, boolean asFloat) {
    super(name);
    this.asFloat = asFloat;
  }
  
  @Override
  public Object apply(WarpScriptStack stack) throws WarpScriptException {
    Object op = stack.pop();
    
    if (!(op instanceof Long)) {
      throw new WarpScriptException(getName() + " operates on a LONG.");
    }
    
    if (this.asFloat) {
      stack.push((double) Float.intBitsToFloat((int) (((long) op) & 0xFFFFFFFFL)));
    } else {
      stack.push(Double.longBitsToDouble((long) op));
    }
        
    return stack;
  }
}
