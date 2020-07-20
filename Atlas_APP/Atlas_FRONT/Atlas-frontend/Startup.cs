using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.UI;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Atlas_frontend.Utils.RestAPI;
using Atlas_frontend.Services;
using Atlas_frontend.Services.Implementation;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection.Extensions;

namespace Atlas_frontend
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDistributedMemoryCache();

            services.AddSession(options =>
            {
                options.IdleTimeout = TimeSpan.FromSeconds(3600);
                options.Cookie.HttpOnly = true;
                options.Cookie.IsEssential = true;
            });

            services.AddControllersWithViews();
            services.AddRazorPages();
            services.AddMvcCore();

            services.TryAddSingleton<IHttpContextAccessor, HttpContextAccessor>();

            DependencyInjection(services);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseDatabaseErrorPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }
            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            //app.UseAuthentication();
            app.UseAuthorization();

            app.UseSession();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");
                endpoints.MapRazorPages();
            });
        }

        public void DependencyInjection(IServiceCollection services)
        {
            services.AddSingleton<IRestAPIClient>(s => new RestAPIClient("http://127.0.0.1:9080/Atlas-backend-web/atlas/api/"));
            services.AddSingleton<IAbsenceService, AbsenceService>();
            services.AddSingleton<IChoixService, ChoixService>();
            services.AddSingleton<ICompteService, CompteService>();
            services.AddSingleton<IEvaluationService, EvaluationService>();
            services.AddSingleton<IFacturationService, FacturationService>();
            services.AddSingleton<IFormationService, FormationService>();
            services.AddSingleton<IFraisService, FraisService>();
            services.AddSingleton<IMissionService, MissionService>();
            services.AddSingleton<IPermissionService, PermissionService>();
            services.AddSingleton<IProjetService, ProjetService>();
            services.AddSingleton<IQuestionService, QuestionService>();
            services.AddSingleton<IRapportService, RapportService>();
            services.AddSingleton<IReclamationService, ReclamationService>();
            services.AddSingleton<IReponseService, ReponseService>();
            services.AddSingleton<IRoleService, RoleService>();
            services.AddSingleton<IRubriqueService, RubriqueService>();
            services.AddSingleton<ISujetService, SujetService>();
            services.AddSingleton<IUserFormationService, UserFormationService>();
        }
    }
}
