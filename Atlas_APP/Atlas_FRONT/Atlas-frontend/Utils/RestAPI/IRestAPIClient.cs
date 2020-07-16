using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.RestAPI
{
    public interface IRestAPIClient
    {
        public Task<TEntity> GetAsync<TEntity>(string path) where TEntity : class;
        public Task<Boolean> PostAsync<TEntity>(string path, TEntity entity) where TEntity : class;
        public Task<TEntity> PutAsync<TEntity>(string path, TEntity entity) where TEntity : class;
        public Task<Boolean> DeleteAsync<TEntity>(string path) where TEntity : class;
    }
}
