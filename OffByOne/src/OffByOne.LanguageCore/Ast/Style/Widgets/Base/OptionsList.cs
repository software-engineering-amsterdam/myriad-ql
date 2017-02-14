namespace OffByOne.LanguageCore.Ast.Style.Widgets.Base
{
    using System.Collections;
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class OptionsList<T> : AstNode, IList<T>
        where T : Literal
    {
        private readonly List<T> options;

        public OptionsList()
        {
            this.options = new List<T>();
        }

        public int Count => this.options.Count;

        public bool IsReadOnly => false;

        public T this[int index]
        {
            get { return this.options[index]; }
            set { this.options[index] = value; }
        }

        public IEnumerator<T> GetEnumerator()
        {
            return this.options.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }

        public void Add(T item)
        {
            this.options.Add(item);
        }

        public void AddRange(IEnumerable<T> items)
        {
            this.options.AddRange(items);
        }

        public void Clear()
        {
            this.options.Clear();
        }

        public bool Contains(T item)
        {
            return this.options.Contains(item);
        }

        public void CopyTo(T[] array, int arrayIndex)
        {
            this.options.CopyTo(array, arrayIndex);
        }

        public bool Remove(T item)
        {
            return this.options.Remove(item);
        }

        public int IndexOf(T item)
        {
            return this.options.IndexOf(item);
        }

        public void Insert(int index, T item)
        {
            this.options.Insert(index, item);
        }

        public void RemoveAt(int index)
        {
            this.options.RemoveAt(index);
        }
    }
}
