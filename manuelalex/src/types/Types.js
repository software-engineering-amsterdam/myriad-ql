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
}

export class QLMoney extends Type {
    render(visitor){
        return visitor.renderMoneyInput(this);
    }
}
export class QLString extends Type {
    render(visitor){
        return visitor.renderStringInput(this);
    }
}
export class QLBoolean extends Type {
    render(visitor){
        return visitor.renderBooleanInput(this);
    }
}

export class QLNumber extends Type {
    render(visitor){
        return visitor.renderNumberInput(this);
    }
}
export class QLDate extends Type {
    render(visitor){
        return visitor.renderDateInput(this);
    }
}
