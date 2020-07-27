using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.RestAPI
{
    public class WSBaseResult
    {
        [JsonProperty("exists")]
        public bool Exists { get; set; }
        [JsonProperty("soldeCongee")]
        public float SoldeCongee { get; set; }
    }
}
