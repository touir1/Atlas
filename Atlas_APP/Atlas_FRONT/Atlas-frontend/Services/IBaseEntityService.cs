using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IBaseEntityService<TEntity> where TEntity : class
    {
        public Task<List<TEntity>> GetListAsync();
        public Task<TEntity> GetAsync(long? id, long? secondId = null);
        public void DeleteAsync(TEntity entity);
        public void UpdateAsync(TEntity entity);
        public void AddAsync(TEntity entity);
    }
}
