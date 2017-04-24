package fr.emse.ai.csp.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klak on 2017-04-24.
 */
public class UniqueLineConstraint implements Constraint{
        private Variable var1;
        private List<Variable> scope;
        private List<ArrayList<Variable>> lines;

        public UniqueLineConstraint(Variable var1, Variable[][] variables) {
            this.var1 = var1;
            scope = new ArrayList<Variable>(2);
            scope.add(var1);
        }


        private boolean isEqual(ArrayList<Variable> line1, ArrayList<Variable> line2,Assignment assignment) {
                for(int i=0;i<line1.size();i++) {
                    if (!(assignment.variableToValue.get(line1.get(i)).equals(assignment.variableToValue.get(line2.get(i))))) {
                        return true;
                    }

                }
                return false;
        }

        @Override
        public List<Variable> getScope() {
            return scope;
        }

        @Override
        public boolean isSatisfiedWith(Assignment assignment) {
            Object value1 = assignment.getAssignment(var1);
            for(ArrayList<Variable> line: lines){
                if(line.contains(var1)) {
                    lines.remove(lines.indexOf(line));
                    for(ArrayList<Variable> line2: lines) {
                        if(line2.equals(line)) {
                            return false;

                        }
                    }
                }
            }

            return value1 == null || !value1.equals(assignment.getAssignment(var2));
        }
    }
}
