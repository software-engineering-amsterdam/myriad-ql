namespace OffByOne.Ql.Common.Collections
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Linq;

    public class OrderedDictionary<TKey, TValue> : IDictionary<TKey, TValue>
    {
        private IList<TKey> keys;
        private IList<TValue> values;

        public OrderedDictionary()
        {
            this.keys = new List<TKey>();
            this.values = new List<TValue>();
        }

        public OrderedDictionary(int capacity)
        {
            this.keys = new List<TKey>(capacity);
            this.values = new List<TValue>(capacity);
        }

        public ICollection<TKey> Keys => this.keys;

        public ICollection<TValue> Values => this.values;

        public int Count => this.keys.Count;

        public bool IsReadOnly => this.values.IsReadOnly;

        private IEnumerable<KeyValuePair<TKey, TValue>> Items =>
            this.keys.Zip(this.values, (x, y) => new KeyValuePair<TKey, TValue>(x, y));

        public TValue this[TKey key]
        {
            get => this.GetValue(key);
            set => this.SetValue(key, value);
        }

        public void Add(TKey key, TValue value)
        {
            if (!this.keys.Contains(key))
            {
                this.keys.Add(key);
                this.values.Add(value);
            }
        }

        public void Add(KeyValuePair<TKey, TValue> item)
        {
            this.Add(item.Key, item.Value);
        }

        public void Clear()
        {
            this.keys.Clear();
            this.values.Clear();
        }

        public bool Contains(KeyValuePair<TKey, TValue> item)
        {
            var keyValuePairs = this.keys.Zip(this.values, (x, y) => new KeyValuePair<TKey, TValue>(x, y));
            return keyValuePairs.Contains(item);
        }

        public bool ContainsKey(TKey key)
        {
            return this.keys.Contains(key);
        }

        public void CopyTo(KeyValuePair<TKey, TValue>[] array, int arrayIndex)
        {
            for (int i = 0; i < this.Count; i++)
            {
                    array[arrayIndex + i] = this.Items.ElementAt(i);
            }
        }

        public IEnumerator<KeyValuePair<TKey, TValue>> GetEnumerator()
        {
            return this.Items.GetEnumerator();
        }

        public bool Remove(TKey key)
        {
            var index = this.keys.IndexOf(key);
            if (index < 0)
            {
                return false;
            }

            this.keys.RemoveAt(index);
            this.values.RemoveAt(index);
            return true;
        }

        public bool Remove(KeyValuePair<TKey, TValue> item)
        {
            return this.Remove(item.Key);
        }

        public bool TryGetValue(TKey key, out TValue value)
        {
            var index = this.keys.IndexOf(key);
            if (index < 0)
            {
                value = default(TValue);
                return false;
            }

            value = this.values[index];
            return true;
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.Items.GetEnumerator();
        }

        private TValue GetValue(TKey key)
        {
            var index = this.keys.IndexOf(key);
            if (index < 0)
            {
                throw new KeyNotFoundException();
            }

            return this.values[index];
        }

        private void SetValue(TKey key, TValue value)
        {
            var index = this.keys.IndexOf(key);
            if (index < 0)
            {
                throw new KeyNotFoundException();
            }

            this.values[index] = value;
        }
    }
}