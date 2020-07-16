using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class PermissionModel
    {
        public long id { get; set; }
        public String ecran { get; set; }
        public String action { get; set; }
        public String application{ get; set; }
        public List<RoleModel> roles { get; set; }
    }
}
