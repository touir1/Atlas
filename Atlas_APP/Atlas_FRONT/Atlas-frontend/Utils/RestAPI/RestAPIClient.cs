using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.RestAPI
{
    public class RestAPIClient : IRestAPIClient
    {
        private HttpClient client;
        
        public RestAPIClient(string basePath)
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(basePath);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public async Task<bool> DeleteAsync<TEntity>(string path) where TEntity : class
        {
            HttpResponseMessage response = await client.DeleteAsync(path);
            return response.IsSuccessStatusCode;
        }

        public async Task<TEntity> GetAsync<TEntity>(string path) where TEntity : class
        {
            TEntity entity = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                entity = await response.Content.ReadAsAsync<TEntity>();
            }
            return entity;
        }

        public async Task<bool> PostAsync<TEntity>(string path, TEntity entity) where TEntity : class
        {
            HttpResponseMessage response = await client.PostAsJsonAsync(path, entity);
            response.EnsureSuccessStatusCode();

            // return URI of the created resource.
            return response.IsSuccessStatusCode;
        }

        public async Task<TEntity> PutAsync<TEntity>(string path, TEntity entity) where TEntity : class
        {
            HttpResponseMessage response = await client.PutAsJsonAsync(path, entity);
            response.EnsureSuccessStatusCode();

            // Deserialize the updated product from the response body.
            entity = await response.Content.ReadAsAsync<TEntity>();
            return entity;
        }
    }
}
