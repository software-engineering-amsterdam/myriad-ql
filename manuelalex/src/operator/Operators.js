/**
 * Created by Manuel on 13/03/2017.
 */

export class Operator {
    /**
     * Abstract
     * @returns {string}
     */
    toString() {
        return 'Operator';
    }

}

export class PlusOperator extends Operator {
    toString(){
        return '+';
    }
}

export class MultiplyOperator extends Operator {
    toString() {
        return '*'
    }
}

export class MinOperator extends Operator {
    toString() {
        return '-';
    }
}

export class DivideOperator extends Operator {
    toString() {
        return '/';
    }
}