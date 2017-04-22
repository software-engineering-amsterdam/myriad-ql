/**
 * Created by Manuel on 20/02/2017.
 */


export class Type {
    constructor(location = null) {
        this.location = location;
    }

    getLocation() {
        return this.location;
    }

    render() {
        throw new Error('Render method should have been overwritten');
    }

    toString(){
        return this.constructor.name;
    }

    getType(){
        return this.constructor;
    }

    /**
     * determines whether operator can be executed on the type
     **/
    isValidOperator(operator) {
        throw new Error('validOperator method should have been overwritten');
    }


}

export class QLMoney extends Type {
    render(visitor){
        return visitor.renderMoneyInput(this);
    }

    renderValue(visitor){
        return visitor.renderMoneyValue(this);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'].includes(operator);
    }

}
export class QLString extends Type {
    render(visitor){
        return visitor.renderStringInput(this);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '=='].includes(operator);
    }
}
export class QLBoolean extends Type {
    render(visitor){
        return visitor.renderBooleanInput(this);
    }


    isValidOperator(operator){
        return ['||', '&&', '==', '!='].includes(operator);
    }
}

export class QLNumber extends Type {
    render(visitor){
        return visitor.renderNumberInput(this);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'].includes(operator);
    }
}
export class QLDate extends Type {
    render(visitor){
        return visitor.renderDateInput(this);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '=='].includes(operator);
    }
}
