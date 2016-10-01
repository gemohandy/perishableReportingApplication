using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PerishableApp.Models;

namespace PerishableApp.Views
{
    public class tblOrdersController : Controller
    {
        private Perishableapp db = new Perishableapp();

        // GET: tblOrders
        public ActionResult Index()
        {
            var tblOrders = db.tblOrders.Include(t => t.tblCompany);
            return View(tblOrders.ToList());
        }

        // GET: tblOrders/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblOrder tblOrder = db.tblOrders.Find(id);
            if (tblOrder == null)
            {
                return HttpNotFound();
            }
            return View(tblOrder);
        }

        // GET: tblOrders/Create
        public ActionResult Create()
        {
            ViewBag.fk_CompanyID = new SelectList(db.tblCompanies, "Id", "Id");
            return View();
        }

        // POST: tblOrders/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,DateTime,isActive,fk_CompanyID")] tblOrder tblOrder)
        {
            if (ModelState.IsValid)
            {
                db.tblOrders.Add(tblOrder);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.fk_CompanyID = new SelectList(db.tblCompanies, "Id", "Id", tblOrder.fk_CompanyID);
            return View(tblOrder);
        }

        // GET: tblOrders/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblOrder tblOrder = db.tblOrders.Find(id);
            if (tblOrder == null)
            {
                return HttpNotFound();
            }
            ViewBag.fk_CompanyID = new SelectList(db.tblCompanies, "Id", "Id", tblOrder.fk_CompanyID);
            return View(tblOrder);
        }

        // POST: tblOrders/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,DateTime,isActive,fk_CompanyID")] tblOrder tblOrder)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tblOrder).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.fk_CompanyID = new SelectList(db.tblCompanies, "Id", "Id", tblOrder.fk_CompanyID);
            return View(tblOrder);
        }

        // GET: tblOrders/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblOrder tblOrder = db.tblOrders.Find(id);
            if (tblOrder == null)
            {
                return HttpNotFound();
            }
            return View(tblOrder);
        }

        // POST: tblOrders/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            tblOrder tblOrder = db.tblOrders.Find(id);
            db.tblOrders.Remove(tblOrder);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
