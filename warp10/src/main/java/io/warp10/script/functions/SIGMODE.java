//
//   Copyright 2017  Cityzen Data
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

/**
 * Toggle the 'signature mode' of the stack
 */
public class SIGMODE extends NamedWarpScriptFunction implements WarpScriptStackFunction {
  
  public SIGMODE(String name) {
    super(name);
  }
  
  @Override
  public Object apply(WarpScriptStack stack) throws WarpScriptException {
    if (Boolean.TRUE.equals(stack.getAttribute(WarpScriptStack.ATTRIBUTE_SIGMODE))) {
      stack.setAttribute(WarpScriptStack.ATTRIBUTE_SIGMODE, null);
    } else {
      stack.setAttribute(WarpScriptStack.ATTRIBUTE_SIGMODE, true);      
    }
    return stack;
  }

}
