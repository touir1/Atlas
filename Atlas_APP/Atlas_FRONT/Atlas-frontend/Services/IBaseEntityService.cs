using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IBaseEntityService<TEntity> where TEntity : class
    {
        public Task<List<TEntity>> GetListAsync(ISession session);
        public Task<TEntity> GetAsync(ISession session, long? id, long? secondId = null);
        public void DeleteAsync(ISession session, long? id, long? secondId = null);
        public void UpdateAsync(ISession session, TEntity entity);
        public void AddAsync(ISession session, TEntity entity);
    }
}
