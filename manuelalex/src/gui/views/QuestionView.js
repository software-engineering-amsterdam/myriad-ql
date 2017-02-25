/**
 * Created by Manuel on 25/02/2017.
 */

import {View}               from 'arva-js/core/View.js';
import {layout}             from 'arva-js/layout/Decorators.js';
import {combineOptions}     from 'arva-js/utils/CombineOptions.js';

import {LabeledTextInput}   from 'arva-kit/input/LabeledTextInput.js';

export class QuestionView extends View {

    @layout.dock.top(~88,0,10)
    @layout.stick.center()
    input = new LabeledTextInput({
        label: this.options.label,
        enabled: true,
        placeholder: this.options.label,
        clearOnEnter: true,
        usesFeedback: false,
    });

    constructor(options = {}) {
        super(options);
    }
}