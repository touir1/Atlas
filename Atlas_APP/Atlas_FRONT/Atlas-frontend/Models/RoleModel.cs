using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class RoleModel
    {
        public long id { get; set; }
        public String libelle { get; set; }
        public List<PermissionModel> permissions { get; set; }
        public List<CompteModel> comptes { get; set; }



    }
}
