using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.RestAPI
{
    public interface IRestAPIClient
    {
        public Task<RestApiResponse<TEntity>> GetAsync<TEntity>(ISession session, string path) 
            where TEntity : class;
        public Task<RestApiResponse<TResultEntity>> PostAsync<TEntity,TResultEntity>(ISession session, string path, TEntity entity) 
            where TEntity : class;
        public Task<RestApiResponse<TResultEntity>> PutAsync<TEntity, TResultEntity>(ISession session, string path, TEntity entity) 
            where TEntity : class;
        public Task<RestApiResponse<TResultEntity>> DeleteAsync<TEntity, TResultEntity>(ISession session, string path) 
            where TEntity : class;
    }
}
