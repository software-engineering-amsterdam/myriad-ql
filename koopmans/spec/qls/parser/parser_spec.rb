require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'
module QLS
  module Parser
    describe QLSParser do
      let(:parser) { QLSParser.new }

      describe 'question' do
        it 'parses' do
          expect(parser.question).to parse('question hasBoughtHouse widget checkbox')
          expect(parser.question).to parse('question hasMaintLoan')
          expect(parser.question).to parse('question hasBoughtHouse {
            widget checkbox
            width: 400
          }')
          # expect(parser.question.parse('question hasBoughtHouse')).to eq('aa')
        end
      end

      describe 'section' do
        it 'parses' do
          expect(parser.section).to parse('section "Buying" question hasBoughtHouse widget checkbox')
          expect(parser.section).to parse('section "Selling" { question hasSoldHouse widget checkbox }')
          expect(parser.section).to parse('section "You sold a house" {
            question sellingPrice
            widget spinbox
            default money {
              width: 400
              font: "Arial"
              fontsize: 14
              color: #999999
              widget spinbox
            }
          }')
        end
      end

      describe 'widget' do
        it 'parses' do
          expect(parser.widget).to parse('widget radio("Yes", "No")')
          expect(parser.widget).to parse('widget checkbox')
        end
      end

      describe 'radio button' do
        it 'parses' do
          expect(parser.radio).to parse('radio("Yes", "No")')
        end
      end

      describe 'default' do
        it 'parses' do
          expect(parser.default).to parse('default money {
            width: 400
            font: "Arial"
            fontsize: 14
            color: #999999
            widget spinbox
          }')
          expect(parser.default).to parse('default boolean widget radio("Yes", "No")')
        end
      end

      describe 'page' do
        it 'parses' do
          expect(parser.page).to parse('page Housing {
            section "Buying" {
              question hasBoughtHouse
            }
          }')

          expect(parser.page).to parse('page Housing {
            section "Buying"
              question hasBoughtHouse
                widget checkbox
            section "Loaning"
              question hasMaintLoan
          }')

          expect(parser.page).to parse('page Housing {
            section "Buying" {
              question hasBoughtHouse
            }
            default boolean widget radio("Yes", "No")
          }')

          expect(parser.page).to parse('page Housing {
            section "Selling" {
              question hasSoldHouse
                widget radio("Yes", "No")
              section "You sold a house" {
                question sellingPrice
                  widget spinbox
              }
            }
          }')
        end
      end

      describe 'stylesheet' do
        it 'parses' do
          expect(parser.stylesheet).to parse('stylesheet taxOfficeExample
            page Housing {
              section "Buying"
                question hasBoughtHouse
                  widget checkbox
              section "Loaning"
                question hasMaintLoan
            }

            page Selling {
              section "Selling" {
                question hasSoldHouse
                  widget radio("Yes", "No")
                section "You sold a house" {
                  question sellingPrice
                    widget spinbox
                  question privateDebt
                    widget spinbox
                  question valueResidue
                  default money {
                    width: 400
                    font: "Arial"
                    fontsize: 14
                    color: #999999
                    widget spinbox
                  }
                }
              }
              default boolean widget radio("Yes", "No")
            }')
        end
      end
    end
  end
end