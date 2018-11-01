package com.spring.obs.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.obs.model.AccountMaster;
import com.spring.obs.model.Payee;
import com.spring.obs.model.Transactions;
import com.spring.obs.model.User;
import com.spring.obs.service.FundService;

@Controller
public class FundController {

	@Autowired
	FundService service;

	int count = 0;
	boolean show= false;

	// ---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/")
	public String showMenu(Model model) {
		String view = "menu";
		model.addAttribute("show", false);
		return view;
	}

	// ---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/fundTransfer")
	public String showFundOptions(Model model) {
		String view = "menu";
		model.addAttribute("show", true);
		return view;
	}

	// ---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/OwnUserAccount")
	public String transferUserAccount(Model model) {
		String view = "ownAccountDetails";
		model.addAttribute("accountExists", new AccountMaster());
		return view;
	}

	// ---------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/OwnUserAccount", method = RequestMethod.POST)
	public String checkAccountid(Model model, HttpServletRequest request,
			@Valid @ModelAttribute("accountExists") AccountMaster acc,
			BindingResult result) {
		String view = "ownAccountDetails";
		if (result.hasErrors()) {
			return view;
		} else {
			boolean accIdFound = service.checkAccountExists(acc.getAccountId());
			ServletContext context = request.getServletContext();
			context.setAttribute("accountId", acc.getAccountId());
			if (accIdFound) {
				List<Long> OwnPayeeAccIdsList = service
						.getOwnMultpileAccIds(acc.getAccountId());
				System.out.println(OwnPayeeAccIdsList.size() + "Size");
				if (OwnPayeeAccIdsList.size() > 0) {
					if (OwnPayeeAccIdsList.contains(acc.getAccountId())) {
						OwnPayeeAccIdsList.remove(acc.getAccountId());
					}
					view = "beneficiary";
					model.addAttribute("ownAccList", OwnPayeeAccIdsList);
				} else {
					model.addAttribute("error",
							"You have no other accounts linked to this username");
					view = "retrieveFail";
				}
			}
			return view;
		}

	}

	// ---------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/ownAcc/{id}")
	public String showPay(@PathVariable("id") long id, Model model,
			HttpServletRequest req) {

		count++;
		String view = "transactionDetails";

		ServletContext context = req.getServletContext();
		context.setAttribute("Id", id);
		long payeeId = (long) context.getAttribute("Id");

		model.addAttribute("PayId", (Object) payeeId);
		model.addAttribute("tranPwd", new User());
		if (count == 1) {
			model.addAttribute("transaction", true);
		} else {

			if (show)
				model.addAttribute("transaction", true);
			else
				model.addAttribute("transaction", false);
		}
		return view;
	}

	@RequestMapping(value = "/ownAcc/{id}", method = RequestMethod.POST)
	public String checkTransactionPwd(Model model,
			@PathVariable("id") long pid,
			@ModelAttribute("tranPwd") User userTransactionPwd,
			HttpServletRequest req) {
		String view = "transactionDetails";

		ServletContext context = req.getServletContext();
		long accId = (long) context.getAttribute("accountId");

		if (service.checkTransactionPassword(userTransactionPwd, pid, accId)) {
			model.addAttribute("amount", new AccountMaster());
			model.addAttribute("transaction", false);
			model.addAttribute("amountForm", true);
			model.addAttribute("append", 1);
			return view;
		} else {
			model.addAttribute("show", true);
			model.addAttribute("transaction", true);
			long payeeId = (long) context.getAttribute("Id");
			model.addAttribute("PayId", (Object)payeeId);
			show = true;
			return view;
		}

	}

	@RequestMapping(value = "/payPayee", method = RequestMethod.POST)
	public String checkAccountntBal(Model model,
			@ModelAttribute("amount") AccountMaster amount,
			BindingResult result, HttpServletRequest req) {

		String view = "fail";

		ServletContext context = req.getServletContext();
		long accId = (long) context.getAttribute("accountId");
		long payeeId = (long) context.getAttribute("Id");

		if (amount.getAccountBalance() <= 0) {
			view = "transactionDetails";
			model.addAttribute("tranPwd", new User());
			model.addAttribute("PayId", (Object)payeeId);
			model.addAttribute("transaction", false);
			model.addAttribute("amountForm", true);
			model.addAttribute("append", 0);
			model.addAttribute("negative", true);
			return view;
		} else {
			if (service.checkSufficientAccountBal(amount, accId)) {
				service.updateFund(accId, amount, payeeId);
				model.addAttribute("append", 1);
				view = "success";
				return view;
			} else {

				model.addAttribute("PayId", (Object) payeeId);
				return view;
			}

		}

	}

	// ---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/otherAccount")
	public String transferOtherAccount(Model model) {
		String view = "otherAccount";
		model.addAttribute("accountNotPresent", false);
		model.addAttribute("accountExists", new AccountMaster());
		return view;
	}

	/*
	 * @RequestMapping(value = "/otherAccount", method = RequestMethod.POST)
	 * public String check(Model model,
	 * 
	 * @Valid @ModelAttribute("accountExists") AccountMaster acc, BindingResult
	 * result, HttpServletRequest req) { String view = "otherAccount"; if
	 * (result.hasErrors()) { return view; } else { boolean accIdFound =
	 * service.checkAccountExists(acc.getAccountId()); if (accIdFound) {
	 * model.addAttribute("accountNotPresent", false); view =
	 * "payeeDetailsOthers"; ServletContext context = req.getServletContext();
	 * context.setAttribute("accountId", acc.getAccountId());
	 * model.addAttribute("payeeDetailsOthers", new Payee()); return view; }
	 * else { model.addAttribute("accountNotPresent", true); return view; } }
	 * 
	 * }
	 */

	@RequestMapping(value = "/otherAccount", method = RequestMethod.POST)
	public String check(Model model,
			@Valid @ModelAttribute("accountExists") AccountMaster acc,
			BindingResult result, HttpServletRequest req) {
		String view = "otherAccount";
		if (result.hasErrors()) {
			return view;
		} else {
			boolean accIdFound = service.checkAccountExists(acc.getAccountId());
			if (accIdFound) {
				model.addAttribute("accountNotPresent", false);
				view = "payeeDetailsOthers";
				ServletContext context = req.getServletContext();
				context.setAttribute("accountId", acc.getAccountId());
				model.addAttribute("payeeDetailsOthers", new Payee());
				// /
				List<Payee> PayeeList = service.retrieveAllPayeesByAccId(acc
						.getAccountId());
				if (PayeeList.size() > 0) {
					context.setAttribute("showTable", true);
					context.setAttribute("payees", PayeeList);
					context.setAttribute("noPayees", false);
				} else {
					context.setAttribute("noPayees", true);
					context.setAttribute("payees", PayeeList);
					context.setAttribute("showTable", false);
				}
				view = "payeeDetailsOthers";
				// /
				return view;
			} else {
				model.addAttribute("accountNotPresent", true);
				return view;
			}
		}

	}

	@RequestMapping(value = "/otherpayeeInfo", method = RequestMethod.POST)
	public String validatePayeeOther(Model model,
			@Valid @ModelAttribute("payeeDetailsOthers") Payee payee,
			BindingResult result, HttpServletRequest req) {

		String view = "payeeDetailsOthers";
		if (result.hasErrors()) {
			return view;
		} else {
			ServletContext context = req.getServletContext();
			context.setAttribute("payeeAccountId", payee.getPayeeAccountId());

			payee.setAccountId((Long) context.getAttribute("accountId"));

			boolean payIdFound = service.checkAccountExists(payee
					.getPayeeAccountId());
			if (payIdFound == true) {
				model.addAttribute("invalidPayee", false);
			} else {
				model.addAttribute("invalidPayee", true);
				return view;
			}
			boolean notMulAccount = service.checkNotMultipleAccount(payee);

			if (notMulAccount == false) {
				model.addAttribute("multipleAccount", true);
				return view;
			} else {
				model.addAttribute("multipleAccount", false);
				boolean isRegPayee = service.isRegisteredPayee(payee);
				if (isRegPayee) {
					boolean validPayeeDetails = service
							.isValidPayeeDetails(payee);
					if (validPayeeDetails == true) {
						view = "transactionOther";
						model.addAttribute("transactionDetails",
								new Transactions());
						model.addAttribute("transPassword", false);
						model.addAttribute("transDescription", true);
						return view;
					} else {
						view = "payeeDetailsOthers";
						model.addAttribute("notValidDetails", true);
						return view;
					}

				} else {
					view = "registerPayee";
					model.addAttribute("registerPayeeDetails", new Payee());
					model.addAttribute("rPayee", true);
					return view;
				}

			}
		}
	}

	@RequestMapping(value = "/registerpayeeInfo"/* , method = RequestMethod.POST */)
	public String registerPayee(Model model,
			@Valid @ModelAttribute("registerPayeeDetails") Payee payee,
			BindingResult result, HttpServletRequest req) {
		String view = "";
		if (result.hasErrors()) {
			view = "registerPayee";
			return view;
		}
		ServletContext context = req.getServletContext();
		// System.out.println("Viewing payee :"+payee.getPayeeAccountId());
		context.setAttribute("payeeAccountId", payee.getPayeeAccountId());
		payee.setAccountId((Long) context.getAttribute("accountId"));
		model.addAttribute("rPayee", false);
		boolean payIdFound = service.checkAccountExists(payee
				.getPayeeAccountId());
		if (payIdFound == true) {
			System.out.println("jkjkhu");
			model.addAttribute("invalidPayee", false);
		} else {
			model.addAttribute("invalidPayee", true);
			model.addAttribute("alreadyPayee", false);
			model.addAttribute("multipleAccount", false);
			model.addAttribute("registrationComplete", false);
			view = "registerPayee";
			return view;
		}
		boolean notMulAccount = service.checkNotMultipleAccount(payee);
		if (notMulAccount == false) {
			model.addAttribute("invalidPayee", false);
			model.addAttribute("alreadyPayee", false);
			model.addAttribute("multipleAccount", true);
			model.addAttribute("registrationComplete", false);
			view = "registerPayee";
			return view;
		} else {
			boolean isRegPayee = service.isRegisteredPayee(payee);
			if (isRegPayee) {
				model.addAttribute("invalidPayee", false);
				model.addAttribute("alreadyPayee", true);
				model.addAttribute("multipleAccount", false);
				model.addAttribute("registrationComplete", false);
				view = "registerPayee";
				return view;
			} else {
				service.registerPayee(payee);
				view = "registerPayee";
				model.addAttribute("transactionDetails", new Transactions());
				model.addAttribute("transPassword", false);
				model.addAttribute("transDescription", true);
				model.addAttribute("notValidAmount", false);
				model.addAttribute("registrationComplete", true);
				return view;
			}
		}

	}

	@RequestMapping(value = "/transactionInfo", method = RequestMethod.POST)
	public String transactionDetails(
			Model model,
			@ModelAttribute("transactionDetails") Transactions transactionDetails,
			@ModelAttribute("transPass") User user, BindingResult result,
			HttpServletRequest req) {
		String view = "transactionOther";
		ServletContext context = req.getServletContext();
		transactionDetails.setAccountId((Long) context
				.getAttribute("accountId"));
		boolean validateTranAmount = service
				.checkTransAmount(transactionDetails);
		model.addAttribute("transPass", new User());
		model.addAttribute("transactionDetails", new Transactions());
		if ((transactionDetails.getTranAmount() <= 0)
				|| (transactionDetails.getTranDescription().length() < 3)) {
			model.addAttribute("nAmount",
					transactionDetails.getTranAmount() <= 0);
			model.addAttribute("inValidDescription", transactionDetails
					.getTranDescription().length() < 3);
			model.addAttribute("transPassword", false);
			model.addAttribute("transDescription", true);
			return view;
		}
		if (validateTranAmount) {
			model.addAttribute("notValidAmount", false);
			model.addAttribute("transPassword", true);
			model.addAttribute("transDescription", false);
			System.out.println(transactionDetails.getTranDescription());
			context.setAttribute("transDetail", transactionDetails);

			return view;
		} else {
			model.addAttribute("transPassword", false);
			model.addAttribute("transDescription", true);
			model.addAttribute("notValidAmount", true);
			model.addAttribute("notValidPassword", false);
			model.addAttribute("inValidDescription", false);
			view = "transactionOther";
			return view;
		}
	}

	@RequestMapping(value = "/transactionPassInfo", method = RequestMethod.POST)
	public String validateTransPassword(Model model,
			@ModelAttribute("transPass") User user, BindingResult result,
			HttpServletRequest req) {
		String view = "";
		ServletContext context = req.getServletContext();
		user.setAccountId((Long) context.getAttribute("accountId"));
		System.out.println((Long) context.getAttribute("accountId"));
		System.out.println("User account " + user.getAccountId());
		boolean validTransPassword = service.isValidTransactionPassword(user);
		if (validTransPassword) {
			System.out.println("test");
			// System.out.println((Transactions)context.getAttribute("transDetail"));
			Transactions transaction = (Transactions) context
					.getAttribute("transDetail");
			long payeeAccountId = ((Long) context
					.getAttribute("payeeAccountId")).longValue();

			System.out.println(payeeAccountId);
			System.out.println(transaction);
			service.addTransaction(transaction, payeeAccountId);
			System.out.println("In Password");
			view = "success";
			return view;
		} else {
			view = "transactionOther";
			model.addAttribute("transPass", new User());
			model.addAttribute("transPassword", true);
			model.addAttribute("transDescription", false);
			model.addAttribute("notValidAmount", false);
			model.addAttribute("notValidPassword", true);
			return view;
		}
	}
	//----------------------------------------------------------------------------------------------------------
	
	@ExceptionHandler(value=Exception.class)
	public String handleException(HttpServletRequest req,Exception ex){
		System.out.println("NumberFormatEx "+ex);
		String view = "inputMismatch";
		return view;
	}
	// ---------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/redirect")
	public String redirect() {
		return "redirect:menu";
	}

	@RequestMapping(value = "/menu")
	public String showMenu() {
		return "menu";
	}

}
