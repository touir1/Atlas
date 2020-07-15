using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.CustomIdentity
{
    public class CustomSignInManager : SignInManager<IdentityUser>
    {
        public CustomSignInManager(CustomUserManager userManager, IHttpContextAccessor contextAccessor, IUserClaimsPrincipalFactory<IdentityUser> claimsFactory, IOptions<IdentityOptions> optionsAccessor, ILogger<CustomSignInManager> logger, IAuthenticationSchemeProvider schemes, IUserConfirmation<IdentityUser> confirmation) 
            : base(userManager, contextAccessor, claimsFactory, optionsAccessor, logger, schemes, confirmation)
        {
        }

        public override Task<SignInResult> PasswordSignInAsync(string userName, string password, bool isPersistent, bool lockoutOnFailure)
        {
            return base.PasswordSignInAsync(userName, password, isPersistent, lockoutOnFailure);
        }

        public override Task<SignInResult> PasswordSignInAsync(IdentityUser user, string password, bool isPersistent, bool lockoutOnFailure)
        {
            return base.PasswordSignInAsync(user, password, isPersistent, lockoutOnFailure);
        }

        public override Task SignOutAsync()
        {
            return base.SignOutAsync();
        }
    }
}
