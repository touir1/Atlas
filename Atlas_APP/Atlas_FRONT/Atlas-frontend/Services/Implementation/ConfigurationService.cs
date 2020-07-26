using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class ConfigurationService : BaseService<ConfigurationModel>, IConfigurationService
    {
        private bool IsInitialized;
        public ConfigurationService(IRestAPIClient restAPIClient) : base(restAPIClient, "configuration")
        {
            IsInitialized = false;
        }

        public async Task<ConfigurationModel> GetByKeyAsync(ISession session, string key)
        {
            if (!IsInitialized)
            {
                await InitConfigurationAsync(session);
            }

            RestApiResponse<ConfigurationModel> response = await _client.GetAsync<ConfigurationModel>(session, $"{_baseServiceUrl}/getByKey/{key}");

            return response.Result;
        }

        public async Task InitConfigurationAsync(ISession session)
        {
            Type configurationKeysType = typeof(ConfigurationKeys);
            foreach(FieldInfo p in configurationKeysType.GetFields(BindingFlags.Static | BindingFlags.Public))
            {
                string key = (string) p.GetValue(null);
                RestApiResponse<ConfigurationModel> response = await _client.GetAsync<ConfigurationModel>(session, $"{_baseServiceUrl}/getByKey/{key}");
                ConfigurationModel configuration = response.Result;
                if(configuration == null)
                {
                    await AddAsync(session, new ConfigurationModel { Key = key, Value = "" });
                }
            }
            IsInitialized = true;

        }

        public override async Task<ConfigurationModel> GetAsync(ISession session, long? id, long? secondId = null)
        {
            if (!IsInitialized)
            {
                await InitConfigurationAsync(session);
            }
            return await base.GetAsync(session, id, secondId);
        }

        public virtual async Task<List<ConfigurationModel>> GetListAsync(ISession session)
        {
            if (!IsInitialized)
            {
                await InitConfigurationAsync(session);
            }
            return await base.GetListAsync(session);
        }
    }
}
