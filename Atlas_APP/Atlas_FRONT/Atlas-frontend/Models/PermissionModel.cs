using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class PermissionModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }
        [JsonProperty("ecran")]
        public String Ecran { get; set; }
        [JsonProperty("action")]
        public String Action { get; set; }
        [JsonProperty("application")]
        public String Application{ get; set; }
        [JsonProperty("roles")]
        public List<RoleModel> Roles { get; set; }
    }
}
