using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.RestAPI
{
    public class RestAPIClient : IRestAPIClient
    {
        public static readonly String TokenKey = "_token";

        private HttpClient client;
        private String _basePath;

        public RestAPIClient(string basePath)
        {
            _basePath = basePath;
            client = new HttpClient();
        }

        public async Task<RestApiResponse<TResultEntity>> DeleteAsync<TEntity, TResultEntity>(ISession session, string path) where TEntity : class
        {
            HttpRequestMessage request = CreateHttpRequestMessage(session, path, HttpMethod.Delete);

            HttpResponseMessage response = await client.SendAsync(request);

            return new RestApiResponse<TResultEntity>
            {
                Result = await response.Content.ReadAsAsync<TResultEntity>(),
                IsSuccess = response.IsSuccessStatusCode,
                StatusCode = response.StatusCode
            };
        }

        public async Task<RestApiResponse<TEntity>> GetAsync<TEntity>(ISession session, string path) where TEntity : class
        {
            HttpRequestMessage request = CreateHttpRequestMessage(session, path, HttpMethod.Get);

            HttpResponseMessage response = await client.SendAsync(request);

            return new RestApiResponse<TEntity>
            {
                Result = await response.Content.ReadAsAsync<TEntity>(),
                IsSuccess = response.IsSuccessStatusCode,
                StatusCode = response.StatusCode
            };
        }

        public async Task<RestApiResponse<TResultEntity>> PostAsync<TEntity, TResultEntity>(ISession session, string path, TEntity entity) where TEntity : class
        {
            HttpRequestMessage request = CreateHttpRequestMessage(session, path, HttpMethod.Post);
            AddEntityAsJson(ref request, entity);

            HttpResponseMessage response = await client.SendAsync(request);

            return new RestApiResponse<TResultEntity>
            {
                Result = await response.Content.ReadAsAsync<TResultEntity>(),
                IsSuccess = response.IsSuccessStatusCode,
                StatusCode = response.StatusCode
            };
        }

        public async Task<RestApiResponse<TResultEntity>> PutAsync<TEntity, TResultEntity>(ISession session, string path, TEntity entity) where TEntity : class
        {
            HttpRequestMessage request = CreateHttpRequestMessage(session, path, HttpMethod.Put);
            AddEntityAsJson(ref request, entity);

            HttpResponseMessage response = await client.SendAsync(request);

            return new RestApiResponse<TResultEntity>
            {
                Result = await response.Content.ReadAsAsync<TResultEntity>(),
                IsSuccess = response.IsSuccessStatusCode,
                StatusCode = response.StatusCode
            };
        }

        private HttpRequestMessage CreateHttpRequestMessage(ISession session, string path, HttpMethod method)
        {
            HttpRequestMessage request = new HttpRequestMessage(method, new Uri(_basePath + path));
            request.Headers.Accept.Clear();
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", session.GetString(RestAPIClient.TokenKey));

            return request;
        }

        private void AddEntityAsJson(ref HttpRequestMessage request, object entity)
        {
            String json = JsonConvert.SerializeObject(entity);
            request.Content = new StringContent(json, Encoding.UTF8, "application/json");
        }
    }
}