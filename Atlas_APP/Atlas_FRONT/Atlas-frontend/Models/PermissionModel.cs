using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class PermissionModel
    {
        public long Id { get; set; }
        public String Ecran { get; set; }
        public String Action { get; set; }
        public String Application{ get; set; }
        public List<RoleModel> Roles { get; set; }
    }
}
