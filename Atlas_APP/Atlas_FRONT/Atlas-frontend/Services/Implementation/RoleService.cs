using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class RoleService : BaseService<RoleModel>, IRoleService
    {
        public RoleService(IRestAPIClient restAPIClient) : base(restAPIClient, "role")
        {
        }

        public async Task<bool> ExistsRole(ISession session, string label)
        {
            try
            {
                RestApiResponse<WSBaseResult> result = await _client.GetAsync<WSBaseResult>(session, $"{_baseServiceUrl}/existsRole/{label}");
                if (result != null && result.Result != null) return result.Result.Exists;
            }
            catch (Exception)
            {
                return false;
            }
            return false;
        }
    }
}
