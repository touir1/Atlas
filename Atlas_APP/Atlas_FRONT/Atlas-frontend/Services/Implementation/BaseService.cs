using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class BaseService<TEntity> : IBaseEntityService<TEntity> where TEntity : class
    {

        protected IRestAPIClient _client;
        protected string _baseServiceUrl;

        public BaseService(IRestAPIClient restAPIClient, string baseServiceUrl)
        {
            _client = restAPIClient;
            _baseServiceUrl = baseServiceUrl;
        }

        public async void AddAsync(ISession session, TEntity entity)
        {
            await _client.PostAsync<TEntity, Boolean>(session, _baseServiceUrl, entity);
        }

        public async void DeleteAsync(ISession session, long? id, long? secondId = null)
        {
            if(secondId != null)
            {
                await _client.DeleteAsync<TEntity, Boolean>(session, $"{_baseServiceUrl}/{id}/{secondId}");
            }
            else
            {
                await _client.DeleteAsync<TEntity, Boolean>(session, $"{_baseServiceUrl}/{id}");
            }
        }

        public async Task<TEntity> GetAsync(ISession session, long? id, long? secondId = null)
        {
            RestApiResponse<TEntity> result = null;
            if(secondId == null)
            {
                result = await _client.GetAsync<TEntity>(session, $"{_baseServiceUrl}/{id}");
            }
            else
            {
                result = await _client.GetAsync<TEntity>(session, $"{_baseServiceUrl}/{id}/{secondId}");
            }
            return result.Result;
        }

        public async Task<List<TEntity>> GetListAsync(ISession session)
        {
            RestApiResponse<List<TEntity>> result = await _client.GetAsync<List<TEntity>>(session, _baseServiceUrl);
            return result.Result;
        }

        public async void UpdateAsync(ISession session, TEntity entity)
        {
            RestApiResponse<TEntity> result = await _client.PutAsync<TEntity, TEntity>(session, _baseServiceUrl, entity);
        }
    }
}
